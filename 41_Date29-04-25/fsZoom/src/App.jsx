import React, { useState, useEffect } from 'react';
import './App.css';

const ImageGallery = () => {
  const images = [
    { id: 10, url: 'https://picsum.photos/id/10/800/600', alt: 'Nature 1' },
    { id: 11, url: 'https://picsum.photos/id/11/800/600', alt: 'Nature 2' },
    { id: 12, url: 'https://picsum.photos/id/12/800/600', alt: 'Nature 3' },
    { id: 13, url: 'https://picsum.photos/id/13/800/600', alt: 'Nature 4' },
    { id: 14, url: 'https://picsum.photos/id/14/800/600', alt: 'Nature 5' },
  ];

  const [currentImageIndex, setCurrentImageIndex] = useState(null);
  const [isOpen, setIsOpen] = useState(false);
  const [scale, setScale] = useState(1);

  const openLightbox = (index) => {
    setCurrentImageIndex(index);
    setIsOpen(true);
    setScale(1); // Reset scale when opening
  };

  const closeLightbox = () => {
    setIsOpen(false);
  };

  const goToPrevious = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + images.length - 1) % images.length);
    setScale(1); // Reset scale when navigating
  };

  const goToNext = () => {
    setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    setScale(1); // Reset scale when navigating
  };

  const zoomIn = () => {
    setScale((prev) => Math.min(prev + 0.2, 3));
  };

  const zoomOut = () => {
    setScale((prev) => Math.max(prev - 0.2, 0.5));
  };

  // Keyboard event handler
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (!isOpen) return;

      switch (e.key) {
        case 'ArrowLeft':
          goToPrevious();
          break;
        case 'ArrowRight':
          goToNext();
          break;
        case 'ArrowUp':
          zoomIn();
          break;
        case 'ArrowDown':
          zoomOut();
          break;
        case 'Escape':
          closeLightbox();
          break;
        default:
          break;
      }
    };

    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [isOpen, currentImageIndex]);

  return (
    <div className="gallery-container">
      <h2>Image Gallery</h2>
      
      {/* First row with 3 images */}
      <div className="image-row">
        {images.slice(0, 3).map((image, index) => (
          <div key={image.id} className="image-item" onClick={() => openLightbox(index)}>
            <img 
              src={image.url.replace('/800/600', '/300/200')}
              alt={image.alt}
              className="thumbnail"
            />
          </div>
        ))}
      </div>
      
      {/* Second row with 2 images */}
      <div className="image-row">
        {images.slice(3, 5).map((image, index) => (
          <div key={image.id} className="image-item" onClick={() => openLightbox(index + 3)}>
            <img 
              src={image.url.replace('/800/600', '/300/200')}
              alt={image.alt}
              className="thumbnail"
            />
          </div>
        ))}
      </div>

      {isOpen && (
        <div className="lightbox">
          <div className="lightbox-content">
            <button className="close-btn" onClick={closeLightbox}>
              &times;
            </button>
            <button className="nav-btn prev-btn" onClick={goToPrevious}>
              &#10094;
            </button>
            <img 
              src={images[currentImageIndex].url}
              alt={images[currentImageIndex].alt}
              className="lightbox-image"
              style={{ transform: `scale(${scale})` }}
            />
            <button className="nav-btn next-btn" onClick={goToNext}>
              &#10095;
            </button>
            <div className="zoom-controls">
              <button onClick={zoomIn} title="Zoom In (↑)">+</button>
              <span>{Math.round(scale * 100)}%</span>
              <button onClick={zoomOut} title="Zoom Out (↓)">-</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ImageGallery;