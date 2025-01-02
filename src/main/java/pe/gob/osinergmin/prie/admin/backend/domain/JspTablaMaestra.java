package pe.gob.osinergmin.prie.admin.backend.domain;

import org.json.JSONArray;

import java.util.List;

public class JspTablaMaestra {
    private CfgTablaAdm tablaSelect;
    private JSONArray jsonModel;
    private List<String> lstColumnas;
    private List<CfgCampoAdm> lstControles;
    private List<cfgListaDominio> lstCombos;
    private List<cfgListaDominio> lstRadios;
    private JSONArray jsonObligatorios;
    private JSONArray jsonPKs;
    private String site_url;
    private boolean flagUsuario;

    public CfgTablaAdm getTablaSelect() {
        return tablaSelect;
    }

    public void setTablaSelect(CfgTablaAdm tablaSelect) {
        this.tablaSelect = tablaSelect;
    }

    public JSONArray getJsonModel() {
        return jsonModel;
    }

    public void setJsonModel(JSONArray jsonModel) {
        this.jsonModel = jsonModel;
    }

    public List<String> getLstColumnas() {
        return lstColumnas;
    }

    public void setLstColumnas(List<String> lstColumnas) {
        this.lstColumnas = lstColumnas;
    }

    public List<CfgCampoAdm> getLstControles() {
        return lstControles;
    }

    public void setLstControles(List<CfgCampoAdm> lstControles) {
        this.lstControles = lstControles;
    }

    public List<cfgListaDominio> getLstCombos() {
        return lstCombos;
    }

    public void setLstCombos(List<cfgListaDominio> lstCombos) {
        this.lstCombos = lstCombos;
    }

    public List<cfgListaDominio> getLstRadios() {
        return lstRadios;
    }

    public void setLstRadios(List<cfgListaDominio> lstRadios) {
        this.lstRadios = lstRadios;
    }

    public JSONArray getJsonObligatorios() {
        return jsonObligatorios;
    }

    public void setJsonObligatorios(JSONArray jsonObligatorios) {
        this.jsonObligatorios = jsonObligatorios;
    }

    public JSONArray getJsonPKs() {
        return jsonPKs;
    }

    public void setJsonPKs(JSONArray jsonPKs) {
        this.jsonPKs = jsonPKs;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    public boolean isFlagUsuario() {
        return flagUsuario;
    }

    public void setFlagUsuario(boolean flagUsuario) {
        this.flagUsuario = flagUsuario;
    }
}
