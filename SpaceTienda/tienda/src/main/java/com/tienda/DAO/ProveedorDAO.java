package com.tienda.DAO;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.tienda.DTO.ProveedorDTO;


public class ProveedorDAO {

	PreparedStatement preparedStatement;
	
	// Metodo para agregar.
	public void registrarProveedores(ProveedorDTO proveedor) 
    {
     Conexion conex= new Conexion();
     try {
      Statement estatuto = conex.getConnection().createStatement();
      estatuto.executeUpdate("INSERT INTO usuarios VALUES ('"+proveedor.getNitProveedor()+"', '"
        +proveedor.getDireccionProveedor()+"', '"+proveedor.getCiudadProveedor()+"','"+proveedor.getNombreProveedor()+"','"+proveedor.getTelefonoProveedor()+"')");
      //JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente el Proveedor","Información",JOptionPane.INFORMATION_MESSAGE);
      estatuto.close();
      conex.desconectar();
      
     } catch (SQLException e) {
               System.out.println(e.getMessage());
      JOptionPane.showMessageDialog(null, "No se Registro el proveedor");
     }
    }
	
	// Metodo editar registro.
    public void editarProveedor(ProveedorDTO proveedor) {
		Conexion conex= new Conexion();
		try {
			Statement st= conex.getConnection().createStatement();
			st.executeUpdate("UPDATE proveedores SET direccion_proveedor ='"+proveedor.getDireccionProveedor()+"', ciudad_proveedor = '"+proveedor.getCiudadProveedor()+"', nombre_proveedor = '"+proveedor.getNombreProveedor()+"', telefono_proveedor = '"+proveedor.getTelefonoProveedor()+"' WHERE nit_proveedor = "+proveedor.getNitProveedor()); 
			//JOptionPane.showMessageDialog(null, "Se ha actualizado Exitosamente","Información",JOptionPane.INFORMATION_MESSAGE);
		      st.close();
		      conex.desconectar();
		}catch(SQLException e) {
            System.out.println(e.getMessage());
            	JOptionPane.showMessageDialog(null, "No se Actualizó el registro");
		}
	}
	
	//Metodo consultar proveedor.
	public ArrayList<ProveedorDTO> consultarProveedor(int nit) {
        ArrayList< ProveedorDTO> miProveedor = new ArrayList<ProveedorDTO>();
        Conexion conex= new Conexion();
          
        try {
         PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM proveedores where nit_proveedor = ? ");
         consulta.setInt(1, nit);
         ResultSet res = consulta.executeQuery();
         
        if(res.next()){
            ProveedorDTO proveedor= new ProveedorDTO();
            proveedor.setNitProveedor(res.getInt("nit_proveedor"));
            proveedor.setDireccionProveedor(res.getString("direccion_proveedor"));
            proveedor.setCiudadProveedor(res.getString("ciudad_proveedor"));
            proveedor.setNombreProveedor(res.getString("nombre_proveedor"));
            proveedor.setTelefonoProveedor(res.getString("telefono_proveedor"));
       
          miProveedor.add(proveedor);
                }
                res.close();
                consulta.close();
                conex.desconectar();
         
        } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "no se pudo consultar el Proveedor\n"+e);
        }
        return miProveedor;
       } 
         
         
        // Metodo listar proveedor.
	    public ArrayList<ProveedorDTO> listaDeProveedores() {
	    	 
	    	 System.out.println("Llego DAO - Proveedor");
	         ArrayList< ProveedorDTO> miProveedor = new ArrayList< ProveedorDTO>();
	         Conexion conex= new Conexion();
	           
	         try {
	          PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM proveedores");
	          ResultSet res = consulta.executeQuery();
	          while(res.next()){
	        	 
	        	  ProveedorDTO proveedor= new ProveedorDTO();
	              proveedor.setNitProveedor(res.getInt("nit_proveedor"));
	              proveedor.setDireccionProveedor(res.getString("direccion_proveedor"));
	              proveedor.setCiudadProveedor(res.getString("ciudad_proveedor"));
	              proveedor.setNombreProveedor(res.getString("nombre_proveedor"));
	              proveedor.setTelefonoProveedor(res.getString("telefono_proveedor"));
	         
	            miProveedor.add(proveedor);
	                 }
	                 res.close();
	                 consulta.close();
	                 conex.desconectar();
	          
	         } catch (Exception e) {
	          JOptionPane.showMessageDialog(null, "no se pudo consultar los proveedores\n"+e);
	         }
	         return miProveedor;

         }
         
	    //Metodo eliminar proveedor.
         public void eliminarProveedor(int nit) {
             Conexion conex = new Conexion();
             try {
                 String query = "DELETE FROM proveedores WHERE nit_proveedor = ?";
                 preparedStatement = conex.getConnection().prepareStatement(query);
                 preparedStatement.setInt(1,nit);
                 preparedStatement.executeUpdate();
             }catch(Exception e) {
                 System.out.println(e.getMessage());
             }
         }
}
