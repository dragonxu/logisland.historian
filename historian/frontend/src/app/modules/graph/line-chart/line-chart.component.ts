import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

import { AbsSubscriberToSelectionOfTagWithRefresh } from '../../../core/AbsSubscriberToSelectionOfTag';
import { Measures } from '../../../measure/Measures';
import { MeasuresService } from '../../../measure/measures.service';
import { MeasuresRequest } from '../../../measure/MeasuresRequest';
import { ProfilService } from '../../../profil/profil.service';
import { ArrayUtil } from '../../../shared/array-util';
import { TimeRangeFilter } from '../../../shared/time-range-selection/time-range-filter';
import { IHistorianTag } from '../../tag/modele/HistorianTag';
import { CartesianAxeType, ILineChartData, ILineChartDataset, ILineChartOption, TimeDistribution } from './LineChartModele';
import { RefreshRateComponent } from '../../../shared/refresh-rate-selection/RefreshRateComponent';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent extends RefreshRateComponent implements OnInit, OnChanges {

  data: ILineChartData;
  options: ILineChartOption;
  @Input() tags: IHistorianTag[];
  @Input() refreshRate: number;
  @Input() timeRange: TimeRangeFilter;
  private colorsForMetrics: Map<string, string> = new Map();
  private colors: string[] = ['#d9080d', '#6aba15', '#241692', '#e23eba',
  '#7e461f', '#7d30b2', '#f5cb82', '#fd3e6f', '#d7e206', '#b6cdce', '#4bc0c0'];


  constructor(private measuresService: MeasuresService,
              protected profilService: ProfilService) {
    super();
    this.data = {
      // labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      // labels: [0, 5, 10, 15, 20, 25, 30],
      datasets: []
    };
    this.options = {
      title: {
        display: false,
        text: 'Line Chart Graph',
        fontSize: 16
      },
      legend: {
        position: 'bottom'
      },
      showLines: true,
      spanGaps: false,
      scales: {
        xAxes: [{
          type: CartesianAxeType.TIME,
          distribution: TimeDistribution.LINEAR,
          // ticks: {
            // suggestedMin: 0,
            // suggestedMax: 30
          // }
      }],
        yAxes: [{
            // stacked: false
            type: CartesianAxeType.LINEAR,
            ticks: {
              // suggestedMin: 30,
              // suggestedMax: 80
            }
        }]
      }
    };
  }

  ngOnInit() {
    super.ngOnInit();
  }

  ngOnChanges(changes: SimpleChanges) {
    super.ngOnChanges(changes);
    if (changes.timeRange && changes.timeRange.currentValue !== changes.timeRange.previousValue) {
      this.updateGraphData();
    }
    if (changes.tags && changes.tags.currentValue !== changes.tags.previousValue) {
      this.updateGraphData();
    }
  }

  updateGraphData() {
    this.data.datasets = [];
    this.tags.forEach(tag => {
      const request = this.buildTagMeasureRequest(tag);
      this.measuresService.get(request).subscribe(m => {
        this.data.datasets.push(this.convertMeasureToDataset(m));
        this.redrawGraph();
      });
    });
    if (this.tags.length === 0) {
      this.redrawGraph();
    }
  }

  redrawGraph() {
    this.data = Object.assign({}, this.data);
  }

  selectData(event) {
    // when user click on a point
    console.log(`Data Selected' : ${this.data.datasets[event.element._datasetIndex].data[event.element._index]}`);
  }

  subscribeToRefreshChanges(t: number): void {
    this.updateGraphData();
  }

  private convertMeasureToDataset(m: Measures): ILineChartDataset {
    const timeSerie = m.timestamps.map((time, index) => {
      return {
        x: time,
        y: m.values[index]
      };
    });
    if (!this.colorsForMetrics.has(m.name)) {
      this.colorsForMetrics.set(m.name, this.getNextColorOrRandomColor());
    }
    return  {
      label: m.name,
      data: timeSerie,
      // cubicInterpolationMode: 'monotone',
      cubicInterpolationMode: 'monotone',
      lineTension: 0,
      fill: false,
      borderColor: this.colorsForMetrics.get(m.name)
    };
  }
  private getNextColorOrRandomColor(): string {
    const color = this.colors.pop();
    if (color) return color;
    return this.getRandomColor();
  }

  private getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }

  private buildTagMeasureRequest(tag: IHistorianTag): MeasuresRequest {
    return new MeasuresRequest({
      itemId: tag.id,
      start: this.timeRange.start,
      end: this.timeRange.end,
      functions: 'savgbckt:300'

    });
  }
}
