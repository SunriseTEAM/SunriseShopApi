import {Component, OnDestroy, OnInit} from '@angular/core';
// import {prod, products} from '../shared/mockData';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from "rxjs";
import {TranslateService} from '../../services/translate.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit, OnDestroy {
  title: string;
  page: any;
  private paramSub: Subscription;
  private querySub: Subscription;
  loading = false;
  selectedBrand="All";
  status ="all";
  productsList: any;
  productStatus = "getAllproduct";
  searchText


  constructor(private productService: ProductService,
              private route: ActivatedRoute,
              public translate: TranslateService
              ) {}

  ngOnInit() {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });
    this.paramSub = this.route.params.subscribe(() => {
      this.update();
    });

  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
    this.paramSub.unsubscribe();
  }

  update() {
    if (this.route.snapshot.queryParamMap.get('page')) {
      const currentPage = +this.route.snapshot.queryParamMap.get('page');
      const size = +this.route.snapshot.queryParamMap.get('size');
      this.getProds(currentPage, size);
    } else {
      this.getProds();
    }
  }
  getProds(page: number = 1, size: number = 12) {
    if (this.route.snapshot.url.length == 1) {
      this.productService.getAllInPage(+page, +size)
        .subscribe(page => {
          this.page = page;
          this.title = 'Get Whatever You Want!';
        });
    } else { //  /category/:id
      const type = this.route.snapshot.url[1].path;
      this.productService.getCategoryInPage(+type, page, size)
        .subscribe(categoryPage => {
          this.title = categoryPage.category;
          this.page = categoryPage.page;
        });
    }
  }
  setLang(lang: string) {
    // console.log("Language", lang);
    this.translate.use(lang).then(() => {});
  }
ShowProduct(productPrice: number) {
    const getAll = this.productStatus === 'getAllproduct';
    const getunder25 = this.productStatus === 'under25' && 25 >= productPrice;
    const get25To50 = this.productStatus === '2550' &&  50 > productPrice && productPrice >= 25;
    const get50To100 = this.productStatus === '50100' &&  100 > productPrice && productPrice >= 50;
    const get100To200 = this.productStatus === '100200' &&  200 > productPrice && productPrice >= 100;
    const get200above = this.productStatus === '200above' && productPrice >= 200;
    return getAll || getunder25 || get25To50 || get50To100 || get100To200 || get200above ;
  }
}
