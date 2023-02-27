package com.weldingco.welding.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Image {
  private Long imagePK;
  private String imageId;
  private Long procedurePK;
  private int width;
  private int height;
  private ImageMimeType mimeType;
  private String name;
  private byte[] data;
}
