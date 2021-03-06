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

package com.comerzzia.web.core.asistente.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.comerzzia.persistencia.core.empresas.AsistenteConfiguracionEmpresaBean;
import com.comerzzia.web.base.Accion;
import com.comerzzia.web.base.Vista;
import com.comerzzia.web.base.WebKeys;

public class VerFormularioAccion extends Accion {

	private static Vista NEXT_PAGE;

	/**
     * Devuelve el nombre de la acción
     * @return String con el nombre de la acción
     */
	public String getNombre() {
		return "verFormulario";
	}
	
	/**
     * Ejecuta la acción
     * @param request Datos de la petición
     * @return Vista con la siguiente pagina a mostrar
     */
	public Vista ejecutar(HttpServletRequest request) {
		try {
			HttpSession sesion = request.getSession();
			
			String operacion = request.getParameter("operacion");
			
			AsistenteConfiguracionEmpresaBean asistente = (AsistenteConfiguracionEmpresaBean) sesion.getAttribute(WebKeys.ASISTENTE_CONFIGURACION_BEAN);
			
			if(asistente.isPasoInicio()){
				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/datosEmpresa.jsp", Vista.INTERNA);
				
			}else if(asistente.isPasoEmpresa()){
				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/logo.jsp", Vista.INTERNA);
				
    		}else if(asistente.isPasoLogo()){
    			if(operacion.equals("siguiente"))
    				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/datosAlmacen.jsp", Vista.INTERNA);    				
    			else
    				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/datosEmpresa.jsp", Vista.INTERNA);
    			
    		}else if(asistente.isPasoAlmacen()){
    			if(operacion.equals("siguiente"))
    				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/desglose.jsp", Vista.INTERNA);    				
    			else
    				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/logo.jsp", Vista.INTERNA);
    			
    		}else if(asistente.isPasoDesglose()){
    			if(operacion.equals("finalizar"))
    				return getControlador().getAccion("salvar").ejecutar(request);  				
    			else
    				NEXT_PAGE = new Vista("core/asistente/mantenimiento/jsp/datosAlmacen.jsp", Vista.INTERNA);
    		}
			
			return NEXT_PAGE;
		}
		catch (Exception e) {
            e.printStackTrace();
            setError(request, e);
            
            return ERROR_PAGE;
		}
	}
}
