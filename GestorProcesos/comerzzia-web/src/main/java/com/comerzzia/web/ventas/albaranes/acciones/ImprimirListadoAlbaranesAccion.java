/**
 * Copyright 2009-2014 RED.ES - Desarrollado por TIER1
 * 
 * Licencia con arreglo a la EUPL, versión 1.1 o -en cuanto 
 * sean aprobadas por la comisión Europea- versiones 
 * posteriores de la EUPL (la "Licencia").
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * 
 * http://ec.europa.eu/idabc/eupl.html
 * 
 * Salvo cuando lo exija la legislación aplicable o se acuerde
 * por escrito, el programa distribuido con arreglo a la
 * Licencia se distribuye "TAL CUAL",
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, 
 * ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige
 * los permisos y limitaciones que establece la Licencia.
 */

package com.comerzzia.web.ventas.albaranes.acciones;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.comerzzia.persistencia.core.informes.TrabajoInformeBean;
import com.comerzzia.servicios.core.informes.InformeException;
import com.comerzzia.util.fechas.Fechas;
import com.comerzzia.web.base.InformeAccion;
import com.comerzzia.web.base.Vista;

public class ImprimirListadoAlbaranesAccion extends InformeAccion {

	public String getNombreInforme() {
		return "ventas.albaranes.lstAlbaranesVentas";
	}
	
	public Vista nuevoInforme(HttpServletRequest request, TrabajoInformeBean trabajoInforme) {
		// Inicializar datos del formulario de lanzamiento del informe
		Date hoy = new Date();
		request.setAttribute("fechaDesde", Fechas.sumaDias(hoy, -30));
		request.setAttribute("fechaHasta", hoy);
		
		return new Vista("ventas/albaranes/buscar/jsp/imprimir.jsp", Vista.INTERNA);
	}
	
	public void imprimirInforme(HttpServletRequest request, TrabajoInformeBean trabajoInforme)throws InformeException {
		// Concepto de Almacén desde
		if (request.getParameter("codConceptoAlmacenDesde").isEmpty()){
			trabajoInforme.addParametro("CODCONALM_DESDE", "0");
		}
		else{
			trabajoInforme.addParametro("CODCONALM_DESDE", request.getParameter("codConceptoAlmacenDesde"));
		}
		// Concepto de Almacén hasta
		if (request.getParameter("codConceptoAlmacenHasta").isEmpty()){
			trabajoInforme.addParametro("CODCONALM_HASTA", "ZZZZ");
		}
		else{
			trabajoInforme.addParametro("CODCONALM_HASTA", request.getParameter("codConceptoAlmacenHasta"));
		}
		
		// Almacén
		if (request.getParameter("codAlmacenDesde").isEmpty()){
			trabajoInforme.addParametro("CODALM_DESDE", "0");
		}
		else{
			trabajoInforme.addParametro("CODALM_DESDE", request.getParameter("codAlmacenDesde"));
		}
		// Almacén
		if (request.getParameter("codAlmacenHasta").isEmpty()){
			trabajoInforme.addParametro("CODALM_HASTA", "ZZZZ");
		}
		else{
			trabajoInforme.addParametro("CODALM_HASTA", request.getParameter("codAlmacenHasta"));
		}
		
		//CodClienteDesde
		if (request.getParameter("codClienteDesde").isEmpty()){
			trabajoInforme.addParametro("CODCLI_DESDE", "0");
		}
		else{
			trabajoInforme.addParametro("CODCLI_DESDE", request.getParameter("codClienteDesde"));
		}
		
		//CodClienteHasta
		if (request.getParameter("codClienteHasta").isEmpty()){
			trabajoInforme.addParametro("CODCLI_HASTA", "ZZZZZZZZZZZ");
		}
		else{
			trabajoInforme.addParametro("CODCLI_HASTA", request.getParameter("codClienteHasta"));
		}

		//CodArticuloDesde
		if (request.getParameter("codArticuloDesde").isEmpty()){
			trabajoInforme.addParametro("CODART_DESDE", "0");
		}
		else{
			trabajoInforme.addParametro("CODART_DESDE", request.getParameter("codArticuloDesde"));
		}
		
		//CodArticuloHasta
		if (request.getParameter("codArticuloHasta").isEmpty()){
			trabajoInforme.addParametro("CODART_HASTA", "ZZZZZZZZZZZ");
		}
		else{
			trabajoInforme.addParametro("CODART_HASTA", request.getParameter("codArticuloHasta"));
		}

		//CodFamiliaDesde
		if (request.getParameter("codFamiliaDesde").isEmpty()){
			trabajoInforme.addParametro("CODFAM_DESDE", "0");
		}
		else{
			trabajoInforme.addParametro("CODFAM_DESDE", request.getParameter("codFamiliaDesde"));
		}
		
		//CodFamiliaHasta
		if (request.getParameter("codFamiliaHasta").isEmpty()){
			trabajoInforme.addParametro("CODFAM_HASTA", "ZZZZZZ");
		}
		else{
			trabajoInforme.addParametro("CODFAM_HASTA", request.getParameter("codFamiliaHasta"));
		}

		try{
			//FechaDesde
			if (request.getParameter("fechaDesde").isEmpty()){
				trabajoInforme.addParametro("FECHA_DESDE", null);
			}
			else{
				Date fechaDesde = Fechas.getFecha(request.getParameter("fechaDesde"));				
				trabajoInforme.addParametro("FECHA_DESDE", fechaDesde);
			}

			//FechaHasta
			if (request.getParameter("fechaHasta").isEmpty()){
				trabajoInforme.addParametro("FECHA_HASTA", null);
			}
			else{
				Date fechaHasta = Fechas.getFecha(request.getParameter("fechaHasta"));
				trabajoInforme.addParametro("FECHA_HASTA", fechaHasta);
			}
			
		}
		catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
