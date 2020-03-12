package com.ust.cloudbatchdemo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Película {

	private String titulo;
	private String genero;
	private String estudio;
	private int puntuacion;
	private int year;
}
