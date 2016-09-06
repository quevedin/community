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

package com.comerzzia.persistencia.tesoreria.tiposefectos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.comerzzia.persistencia.core.config.ConfigEmpresaBean;
import com.comerzzia.util.base.MantenimientoDao;
import com.comerzzia.util.db.Connection;
import com.comerzzia.util.db.PreparedStatement;
import com.comerzzia.util.log.Logger;
import com.comerzzia.util.paginacion.PaginaResultados;

public class TiposEfectosDao extends MantenimientoDao {

	private static String TABLA = "D_TIPOS_EFECTOS_TBL";
	
	private static Logger log = Logger.getMLogger(TiposEfectosDao.class);
	
	public static PaginaResultados consultar(Connection conn, ConfigEmpresaBean config, 
			ParametrosBuscarTiposEfectosBean param) throws SQLException {
		Statement stmt = null;
    	ResultSet rs = null;
    	
    	// Inicializamos la página de resultados
    	List<TipoEfectoBean> resultados = new ArrayList<TipoEfectoBean>(param.getTamañoPagina());
    	PaginaResultados paginaResultados = new PaginaResultados(param, resultados);
    	
    	// Consultas
    	String sql = obtenerConsulta(config, param);
    	String sqlCount = obtenerConsultaCount(config, param);
    	
        try {
    		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    		
    		// Obtenemos número de resultados
        	log.debug("consultar() - " + sqlCount);
        	rs = stmt.executeQuery(sqlCount);
        	if (rs.next()){
        		paginaResultados.setTotalResultados(rs.getInt(1));
        	}
        	rs.close();
        	
        	// Si tenemos resultados, obtenemos la pagina requerida
            if (paginaResultados.getTotalResultados() > 0) {
            	log.debug("consultar() - " + sql);
                rs = stmt.executeQuery(sql);
                
                int contador = 0;
                if (rs.absolute(paginaResultados.getInicio())) {
                    do {
                		TipoEfectoBean tipoEfecto = new TipoEfectoBean();
                		tipoEfecto.setCodTipoEfecto(rs.getString("CODTIPOEFEC"));
                		tipoEfecto.setDesTipoEfecto(rs.getString("DESTIPOEFEC"));
                		tipoEfecto.setRemesable(rs.getString("REMESABLE"));
                		tipoEfecto.setEntradaDocumentoAutomatica(rs.getString("ENTRADA_DOCUMENTO_AUTOMATICA"));
                		tipoEfecto.setActivo(rs.getString("ACTIVO"));
                		resultados.add(tipoEfecto);
                        contador++;
                    } while (rs.next() && contador < paginaResultados.getTamañoPagina());                   
                }        		
        	} 
            return paginaResultados;
        }
            finally {
        		try {
        			rs.close();
        		}
        		catch(Exception ignore) {;}
        		try {
        			stmt.close();
        		}
        		catch(Exception ignore) {;}
        	}
	} 
    
	
	public static String obtenerConsulta(ConfigEmpresaBean config, ParametrosBuscarTiposEfectosBean param) {
		String sql = null;
		
		sql = "SELECT CODTIPOEFEC, DESTIPOEFEC, REMESABLE, ENTRADA_DOCUMENTO_AUTOMATICA, ACTIVO " +
		      "FROM " + getNombreElemento(config.getEsquemaEmpresa(), TABLA);
		
		// Clausula WHERE
        String where = getClausulaWhere(param);
        if (where.length() > 0) {
            sql += "WHERE " + where;
        }
        
        // Clausula ORDER BY
        if (!param.getOrden().isEmpty()) {
        	sql += "ORDER BY " + param.getOrden();
        }
        
		return sql;
	}
	
	
	public static String obtenerConsultaCount(ConfigEmpresaBean config, ParametrosBuscarTiposEfectosBean param) {
		String sql = null;
		
		sql = "SELECT COUNT(*) " +
		      "FROM " + getNombreElemento(config.getEsquemaEmpresa(), TABLA);
		
		// Clausula WHERE
        String where = getClausulaWhere(param);
        if (where.length() > 0) {
            sql += "WHERE " + where;
        }
        
		return sql;
	}

	
	private static String getClausulaWhere(ParametrosBuscarTiposEfectosBean param) {
		String where = "";
		
		// CODSERIE
		if (!param.getCodTipoEfecto().isEmpty()) {
			where = "CODTIPOEFEC LIKE '" + param.getCodTipoEfecto() + "%' ";
		}
		
		// DESSERIE
		if (!param.getDesTipoEfecto().isEmpty()) {
			if(!where.isEmpty()){
    			where += "AND ";
    		}
			where += "UPPER(DESTIPOEFEC) LIKE UPPER('" + param.getDesTipoEfecto() + "%') ";
		}
		
		if (!param.getActivo().isEmpty()) {
			if(!where.isEmpty()){
    			where += "AND ";
    		}
			where += "ACTIVO = '"+ param.getActivo() +"' ";
		}
		
		return where;
	} 
	
	
    public static TipoEfectoBean consultar(Connection conn, ConfigEmpresaBean config, String codTipoEfecto) throws SQLException {
		PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	TipoEfectoBean tipoEfecto = null;
    	String sql = null;
    	
    	sql = "SELECT CODTIPOEFEC, DESTIPOEFEC, REMESABLE, ENTRADA_DOCUMENTO_AUTOMATICA, ACTIVO " +
    	      "FROM " + getNombreElemento(config.getEsquemaEmpresa(), TABLA) +
		      "WHERE CODTIPOEFEC = ?";
    	
    	try {
    		pstmt = new PreparedStatement(conn, sql);
    		pstmt.setString(1, codTipoEfecto);
        	log.debug("consultar() - " + pstmt);
        	
            rs = pstmt.executeQuery();
        	
        	if (rs.next()){
        		tipoEfecto = new TipoEfectoBean(); 
        		tipoEfecto.setCodTipoEfecto(rs.getString("CODTIPOEFEC"));
        		tipoEfecto.setDesTipoEfecto(rs.getString("DESTIPOEFEC"));
        		tipoEfecto.setRemesable(rs.getString("REMESABLE"));
        		tipoEfecto.setEntradaDocumentoAutomatica(rs.getString("ENTRADA_DOCUMENTO_AUTOMATICA"));
        		tipoEfecto.setActivo(rs.getString("ACTIVO"));
        	}
        	
    		return tipoEfecto;
    	}
    	finally {
    		try{
    			rs.close();
    		}
    		catch(Exception ignore) {;}
    		try{
    			pstmt.close();
    		}
    		catch(Exception ignore) {;}
    	}
	} 
    
    
    public static void insert(Connection conn, ConfigEmpresaBean config, TipoEfectoBean tipoEfecto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = null;
		
		sql = "INSERT INTO " + getNombreElemento(config.getEsquemaEmpresa(), TABLA) + 
		      "(CODTIPOEFEC, DESTIPOEFEC, REMESABLE, ENTRADA_DOCUMENTO_AUTOMATICA, ACTIVO) " +
		      "VALUES (?, ?, ?, ?, ?)";
		
		try {
			pstmt = new PreparedStatement(conn, sql);
        	pstmt.setString(1, tipoEfecto.getCodTipoEfecto());
        	pstmt.setString(2, tipoEfecto.getDesTipoEfecto());
        	pstmt.setString(3, tipoEfecto.getRemesable());
        	pstmt.setString(4, tipoEfecto.getEntradaDocumentoAutomatica());
        	pstmt.setString(5, tipoEfecto.getActivo());
        	
        	log.debug("insert() - " + pstmt);
        	
        	pstmt.execute();
		}
		catch (SQLException e) {
			throw getDaoException(e);
		}
		finally {
    		try {
    			pstmt.close();
    		}
    		catch(Exception ignore) {;}
    	}
	}
    
    
    public static void update(Connection conn, ConfigEmpresaBean config, TipoEfectoBean tipoEfecto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = null;
		
		sql = "UPDATE " + getNombreElemento(config.getEsquemaEmpresa(), TABLA) +
		      "SET DESTIPOEFEC = ?, REMESABLE = ?, ENTRADA_DOCUMENTO_AUTOMATICA = ?, ACTIVO = ? " +
		      "WHERE CODTIPOEFEC = ?";
		
		try {
			pstmt = new PreparedStatement(conn, sql);		
        	pstmt.setString(1, tipoEfecto.getDesTipoEfecto());
        	pstmt.setString(2, tipoEfecto.getRemesable());
        	pstmt.setString(3, tipoEfecto.getEntradaDocumentoAutomatica());
        	pstmt.setString(4, tipoEfecto.getActivo());
        	pstmt.setString(5, tipoEfecto.getCodTipoEfecto());
        	
        	log.debug("update() - " + pstmt);
        	
        	pstmt.execute();
		}
		catch (SQLException e) {
			throw getDaoException(e);
		}
		finally {
    		try {
    			pstmt.close();
    		}
    		catch(Exception ignore) {;}
    	}
	}
        
    
    public static void delete(Connection conn, ConfigEmpresaBean config, TipoEfectoBean tipoEfecto) throws SQLException {
		delete(conn, config, tipoEfecto.getCodTipoEfecto());
	}
    	
    	
    public static void delete(Connection conn, ConfigEmpresaBean config, String codTipoEfecto) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = null;
		
		sql = "DELETE FROM " + getNombreElemento(config.getEsquemaEmpresa(), TABLA) +
		      "WHERE CODTIPOEFEC = ?";
		
		try {
			pstmt = new PreparedStatement(conn, sql);
        	pstmt.setString(1, codTipoEfecto);
        	
        	log.debug("delete() - " + pstmt);
        	
        	pstmt.execute();
		}
		catch (SQLException e) {
			throw getDaoException(e);
		}
		finally {
    		try {
    			pstmt.close();
    		}
    		catch(Exception ignore) {;}
    	}
	}
}