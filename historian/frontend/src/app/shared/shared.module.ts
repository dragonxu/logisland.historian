import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { ButtonLabelledComponent } from './button-labelled/button-labelled.component';
import { DynamicFormQuestionComponent } from './dynamic-form/dynamic-form-question/dynamic-form-question.component';
import { DynamicFormComponent } from './dynamic-form/dynamic-form.component';
import { SearchComponent } from './search/search.component';
import { DynamicFormQuestionArrayComponent } from './dynamic-form/dynamic-form-question-array/dynamic-form-question-array.component';

import { ChartModule } from 'primeng/chart';
import { DataViewModule } from 'primeng/dataview';
import { DropdownModule } from 'primeng/dropdown';
import { MenuModule } from 'primeng/menu';
import { PanelMenuModule } from 'primeng/panelmenu';
import { PanelModule } from 'primeng/panel';
import { TreeModule } from 'primeng/tree';
import { TreeDragDropService } from 'primeng/api';
import { AccordionModule } from 'primeng/accordion';
import { TabViewModule } from 'primeng/tabview';
import { InputSwitchModule } from 'primeng/inputswitch';
import { TooltipModule } from 'primeng/tooltip';
import { ButtonModule } from 'primeng/button';
import { GrowlModule } from 'primeng/growl';
import { RefreshRateSelectionComponent } from './refresh-rate-selection/refresh-rate-selection.component';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ChartModule,
    DataViewModule,
    DropdownModule,
    MenuModule,
    PanelMenuModule,
    FormsModule,
    PanelModule,
    TreeModule,
    AccordionModule,
    TabViewModule,
    InputSwitchModule,
    TooltipModule,
    ButtonModule,
    GrowlModule,
    ConfirmDialogModule,
    DialogModule,
  ],
  exports: [
    CommonModule,
    ReactiveFormsModule,
    SearchComponent,
    ButtonLabelledComponent,
    DynamicFormComponent,
    DynamicFormQuestionComponent,
    ChartModule,
    DataViewModule,
    DropdownModule,
    MenuModule,
    PanelMenuModule,
    FormsModule,
    PanelModule,
    TreeModule,
    AccordionModule,
    RefreshRateSelectionComponent,
    TabViewModule,
    InputSwitchModule,
    TooltipModule,
    ButtonModule,
    GrowlModule,
    ConfirmDialogModule,
    DialogModule,
  ],
  providers: [
    TreeDragDropService,
  ],
  declarations: [
    SearchComponent,
    ButtonLabelledComponent,
    DynamicFormComponent,
    DynamicFormQuestionComponent,
    DynamicFormQuestionArrayComponent,
    RefreshRateSelectionComponent,
  ],
})
export class SharedModule { }