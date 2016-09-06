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

package com.comerzzia.web.backoffice.regularizaciones.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.comerzzia.servicios.almacenes.regularizaciones.Regularizacion;
import com.comerzzia.servicios.almacenes.regularizaciones.RegularizacionException;
import com.comerzzia.servicios.almacenes.regularizaciones.RegularizacionNotFoundException;
import com.comerzzia.servicios.almacenes.regularizaciones.ServicioRegularizaciones;
import com.comerzzia.servicios.core.permisos.PermisosEfectivosAccionBean;
import com.comerzzia.servicios.core.sesion.DatosSesionBean;
import com.comerzzia.web.backoffice.regularizaciones.ui.FormularioRegularizacionBackofficeBean;
import com.comerzzia.web.base.Accion;
import com.comerzzia.web.base.Vista;
import com.comerzzia.web.base.WebKeys;

public class VerAccion extends Accion {

	/**
	 * Devuelve el nombre de la acción
	 * 
	 * @return String con el nombre de la acción
	 */
	public String getNombre() {
		return "ver";
	}

	/**
	 * Ejecuta la acción
	 * 
	 * @param request
	 *            Datos de la petición
	 * @return Vista con la siguiente pagina a mostrar
	 */
	public Vista ejecutar(HttpServletRequest request) {
		try {
			HttpSession sesion = request.getSession();
			DatosSesionBean datosSesion = (DatosSesionBean) sesion
					.getAttribute(WebKeys.DATOS_SESION);

			// Comprobamos los permisos de la acción
			PermisosEfectivosAccionBean permisos = datosSesion
					.getPermisosCache(this.getAccionMenu());
			if (!permisos.isPuedeEjecutar()) {
				return SIN_PERMISO;
			}

			// Obtenemos la regularización
			Long idRegularizacion = new Long(request
					.getParameter(WebKeys.ID_OBJETO));
			Regularizacion regularizacion = ServicioRegularizaciones.consultar(
					idRegularizacion, datosSesion);

			// Inicalizamos el formulario
			FormularioRegularizacionBackofficeBean formulario = (FormularioRegularizacionBackofficeBean) sesion
					.getAttribute(WebKeys.FORMULARIO_REGULARIZACION_BACKOFFICE);
			formulario.inicializar(regularizacion);

			// Mostramos el formulario
			return getControlador().getAccion("verFormulario")
					.ejecutar(request);
		} catch (RegularizacionNotFoundException e) {
			// Si no se encuentra el registro volvemos al buscador y enviamos en
			// mensaje de error
			setMensajeError(request, "No se ha encontrado el registro", e);
			return getControlador().getAccion("buscar").ejecutar(request);
		} catch (RegularizacionException e) {
			setError(request, e);
			return ERROR_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
			setError(request, e);

			return ERROR_PAGE;
		}
	}
}