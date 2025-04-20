import { bootstrapApplication } from '@angular/platform-browser';
import { SearchComponent } from './app/search/search.component';
import { provideHttpClient } from '@angular/common/http';

bootstrapApplication(SearchComponent, {
  providers: [provideHttpClient()]
}).catch(err => console.error(err));
