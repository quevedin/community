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

package com.comerzzia.web.ventas.tickets.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.comerzzia.servicios.core.permisos.PermisosEfectivosAccionBean;
import com.comerzzia.servicios.core.sesion.DatosSesionBean;
import com.comerzzia.servicios.ventas.tickets.errores.ErrorTicketConstraintViolationException;
import com.comerzzia.servicios.ventas.tickets.errores.ErrorTicketException;
import com.comerzzia.servicios.ventas.tickets.errores.ServicioErroresTickets;
import com.comerzzia.web.base.Accion;
import com.comerzzia.web.base.Vista;
import com.comerzzia.web.base.WebKeys;


public class EliminarTicketErroneoAccion extends Accion {

	/**
     * Devuelve el nombre de la acción
     * @return String con el nombre de la acción
     */
    public String getNombre() {
        return "eliminarTicket";
    }
    
    /**
     * Ejecuta la acción
     * @param request Datos de la petición
     * @return Vista con la siguiente pagina a mostrar
     */
    public Vista ejecutar(HttpServletRequest request) {
    	HttpSession sesion = request.getSession();
    	try {
    		DatosSesionBean datosSesion = (DatosSesionBean) sesion.getAttribute(WebKeys.DATOS_SESION);
    		
    		// Comprobamos los permisos de la acción
    		PermisosEfectivosAccionBean permisos = datosSesion.getPermisosCache(this.getAccionMenu());
    		if (!permisos.isPuedeEliminar()) {
    			return SIN_PERMISO;
    		}
    		
    		// Recogemos el uid del ticket
    		String uidTicket = request.getParameter(WebKeys.ID_OBJETO);
        	ServicioErroresTickets.eliminar(uidTicket, datosSesion);                   

    		setMensaje(request, "El ticket se ha eliminado correctamente");
    		
    		return getControlador().getAccion("verFormulario").ejecutar(request);
    	} 
    	catch (ErrorTicketConstraintViolationException e) {
    		setMensajeError(request, "No se ha podido eliminar el ticket porque existen registros asociados");
   			return getControlador().getAccion("verFormulario").ejecutar(request);
		}
    	catch (ErrorTicketException e) {
    		setError(request, e);
            return ERROR_PAGE;
		} 
		catch (Exception e) {
            e.printStackTrace();
            setError(request, e);
            
            return ERROR_PAGE;
        }
    }
}
