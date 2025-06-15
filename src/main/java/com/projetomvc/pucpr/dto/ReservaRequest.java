package com.projetomvc.pucpr.dto;

public class ReservaRequest {
    private String modelo;
    private String dataInicio;
    private String dataFim;
    private double precoPorDia;

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public double getPrecoPorDia() { return precoPorDia; }
    public void setPrecoPorDia(double precoPorDia) { this.precoPorDia = precoPorDia; }
}
