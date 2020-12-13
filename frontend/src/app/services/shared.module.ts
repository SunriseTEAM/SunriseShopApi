import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FilterByBrandPipe } from "./filterByBrand.pipe";


@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [

    FilterByBrandPipe,
  ],
  exports: [

    FilterByBrandPipe,

  ],
  providers: [

  ],
})
export class SharedModule {}
