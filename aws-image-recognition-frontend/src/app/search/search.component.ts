import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  selectedFile: File | null = null;
  searchTerm: string = 'Ball';
  results: string[] = [];
  allImages: string[] = [];
  previewImage: string | null = null;

  constructor(private http: HttpClient) {}

  openPreview(imageUrl: string): void {
    this.previewImage = imageUrl;
  }

  closePreview(): void {
    this.previewImage = null;
  }

  ngOnInit(): void {
    this.fetchAllImages();
  }

  fetchAllImages(): void {
    this.http.get<string[]>(`${environment.apiBaseUrl}/s3/all`)
      .subscribe({
        next: data => this.allImages = data,
        error: err => console.error('Failed to fetch all images', err)
      });
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  upload(): void {
    if (!this.selectedFile) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.http.post(`${environment.apiBaseUrl}/images/upload`, formData, { responseType: 'text' })
      .subscribe({
        next: res => {
          alert(res);
          this.fetchAllImages();
        },
        error: err => console.error('Upload failed', err)
      });
  }

  search(): void {
    this.http.post<string[]>(`${environment.apiBaseUrl}/images/search/${this.searchTerm}`, {})
      .subscribe({
        next: data => this.results = data,
        error: err => console.error('Search failed', err)
      });
  }

}
