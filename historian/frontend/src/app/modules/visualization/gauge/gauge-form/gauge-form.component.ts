import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { BaseDynamicFormComponentEmitter } from '../../../../shared/dynamic-form/BaseDynamicFormComponentEmitter';
import { Operation } from '../../../datasource/ConfigurationToApply';
import { QuestionControlService } from '../../../../shared/dynamic-form/question-control.service';
import { ConfirmationService } from 'primeng/components/common/confirmationservice';
import { ZoneRange, ZoneRangeColors } from '../../../graph/gauge-chart/gauge';
import { FormBuilder } from '@angular/forms';


export interface BackendGaugeConfig {
  value: number | string;
  min: number | string;
  max: number | string;
  greenZones: ZoneRange[];
  yellowZones: ZoneRange[];
  redZones: ZoneRange[];
}

@Component({
  selector: 'app-gauge-form',
  templateUrl: './gauge-form.component.html',
  styleUrls: ['./gauge-form.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class GaugeFormComponent extends BaseDynamicFormComponentEmitter<BackendGaugeConfig> {

  protected formOperation: Operation = Operation.UPDATE;

  constructor(protected qcs: QuestionControlService,
              protected confirmationService: ConfirmationService,
              private fb: FormBuilder) {
    super(qcs, confirmationService);
  }

  protected create(): BackendGaugeConfig {
    return {
      value: 500,
      min: 0,
      max: 1000,
      greenZones : [
         { from: 250, to : 750 }
      ],
      yellowZones : [
         { from: 175, to : 250 },
         { from: 750, to : 825 }
      ],
      redZones : [
         { from: 0, to : 175 },
         { from: 825, to : 1000 }
      ]
    };
  }

  protected prepareSaveItem(): BackendGaugeConfig {
    const item: BackendGaugeConfig = this.create();
    Object.assign(item, this.item);
    Object.assign(item, this.form.value);
    delete (item as any).zoneranges;
    item.greenZones = this.form.value.zoneranges.filter(z => z.color === ZoneRangeColors.GREEN);
    item.yellowZones = this.form.value.zoneranges.filter(z => z.color === ZoneRangeColors.YELLOW);
    item.redZones = this.form.value.zoneranges.filter(z => z.color === ZoneRangeColors.RED);    
    return item;
  }

  protected rebuildForm(): void {
    const objForForm: any = Object.assign({}, this.item);
    objForForm.zoneranges = objForForm.greenZones.map(z => {
      z.color = ZoneRangeColors.GREEN;
      return z;
    }).concat(objForForm.yellowZones.map(z => {
      z.color = ZoneRangeColors.YELLOW;
      return z;
    }), objForForm.redZones.map(z => {
      z.color = ZoneRangeColors.RED;
      return z;
    }));

    const zoneRangesFGs = objForForm.zoneranges.map(z => this.fb.group(z));
    const zoneRangeFormArray = this.fb.array(zoneRangesFGs);
    this.form.setControl('zoneranges', zoneRangeFormArray);
    this.form.reset(objForForm);
  }

}
