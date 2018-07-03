import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { TreeNode } from 'primeng/api';
import { Observable } from 'rxjs/Observable';
import { map } from 'rxjs/operators';

import { TagsSelection } from '../../selection/Selection';
import { IHistorianTag } from '../modele/HistorianTag';
import { TagHistorianService } from '../service/tag-historian.service';
import { NgTreenodeService } from './ng-treenode.service';
import { ProfilService } from '../../../profil/profil.service';

@Component({
  selector: 'app-historian-tag-tree',
  templateUrl: './historian-tag-tree.component.html',
  styleUrls: ['./historian-tag-tree.component.css']
})
export class HistorianTagTreeComponent implements OnInit, OnChanges {

  @Input() tagsSelection: TagsSelection;

  loading = false;
  treeNodes: TreeNode[];
  selectedNodes: TreeNode[];

  constructor(private ngTreenodeService: NgTreenodeService,
              private tagService: TagHistorianService,
              private profilService: ProfilService) { }

  ngOnInit() {
    this.selectedNodes = [];
    this.getNodeTree().subscribe(treeNodes => {
      this.treeNodes = treeNodes;
      this.expandAll();
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.tagsSelection && !changes.tagsSelection.isFirstChange()) {
      this.selectedNodes.forEach(node => {
        this.restoreNodeTree(node);
      });
      this.selectedNodes = [];
      this.treeNodes.forEach(node => {
        this.initializeTreeWithTagsSelection(node, this.tagsSelection);
      });
    }
  }

  selectNode(event) {
    this.selectRecursive(event.node, true);
  }

  unSelectNode(event) {
    this.selectRecursive(event.node, false);
  }

  loadNode(event) {
    this.loadANodeIfNeeded(event.node);
  }

  saveSelection() {

  }

  revertSelection() {

  }

  expandAll() {
    this.treeNodes.forEach(node => {
        this.expandRecursive(node, true, true);
    } );
  }

  collapseAll() {
    this.treeNodes.forEach( node => {
        this.expandRecursive(node, false, false);
    } );
  }

  private getNodeTree(): Observable<TreeNode[]> {
    return this.ngTreenodeService.getHistTagTree();
  }

  private initializeTreeWithTagsSelection(node: TreeNode, tagsSelection: TagsSelection): void {
    if (node && node.type === 'tag' && tagsSelection.containTag(node.data.id)) {
        node.icon = this.findIcon(true);
        this.selectedNodes.push(node);
    }
    if (node.children) {
        node.children.forEach( childNode => {
            this.initializeTreeWithTagsSelection(childNode, tagsSelection);
        } );
    }
  }

  private expandRecursive(node: TreeNode, isExpand: boolean, loadChildren: boolean) {
    node.expanded = isExpand;
    if (loadChildren) this.loadANodeIfNeeded(node);
    if (node.children) {
        node.children.forEach( childNode => {
            this.expandRecursive(childNode, isExpand, loadChildren);
        } );
    }
  }
  /** Change icon of selected or unselected tags
   * add or remove them from current tagsSelection
   *
   * We do not need to add them in selectedNodes as it is done by defaut using checkbox selection
  */
  private selectRecursive(node: TreeNode, isSelected: boolean) {
    if (node.type === 'tag') {
      node.icon = this.findIcon(isSelected);
      if (isSelected) {
        this.tagsSelection.addTag(node.data.id);
        this.profilService.addTag(node.data);
      } else {
        this.tagsSelection.removeTag(node.data.id);
        this.profilService.removeTag(node.data);
      }
    }
    if (node.children) {
        node.children.forEach(childNode => {
            this.selectRecursive(childNode, isSelected);
        } );
    }
  }

  private restoreNodeTree(node: TreeNode) {
    if (node.type === 'tag') {
      node.icon = this.findIcon(false);
    }
    if (node.children) {
        node.children.forEach(childNode => {
            this.restoreNodeTree(childNode);
        } );
    }
  }

  private findIcon(isSelected: boolean): string {
    if (isSelected) return 'in-dataset';
    return 'historian-tag';
  }

  private loadANodeIfNeeded(node: TreeNode): boolean {
    if (node && node.type === 'group' && (!node.children  || node.children.length === 0)) {
      this.loadChildren(node);
      return true;
    }
    return false;
  }

  private getChildren(node: TreeNode): Observable<TreeNode[]> {
    const query: string = this.buildQuery(node);
    return this.tagService.getQuery(query).pipe(
      map(tags => this.createNodesFromTags(tags))
    );
  }

  private createNodesFromTags(tags: IHistorianTag[]): TreeNode[] {
    const nodes = this.createNodes(tags);
    nodes.forEach(node => {
      const tagId = (node.data as IHistorianTag).id;
      if (this.tagsSelection.containTag(tagId)) {
        this.selectedNodes.push(node);
        this.selectNode(node);
      }
    });
    return nodes;
  }

  private loadChildren(node: TreeNode): void {
    this.loading = true;
    this.getChildren(node).subscribe(nodes => {
      node.children = nodes;
      this.loading = false;
    });
  }

  private createNodes(tags: IHistorianTag[]): TreeNode[] {
    const children = tags.map(tag => {
      return {
          label: tag.tag_name,
          data: tag,
          icon: 'historian-tag',
          leaf: true,
          type: 'tag',
      };
    });
    return children;
  }

  private buildQuery(node: TreeNode): string {
    const query = `${node.type}:"${node.label}"`;
    if (node.parent) return `${query} AND ${this.buildQuery(node.parent)}`;
    return query;
  }
}
