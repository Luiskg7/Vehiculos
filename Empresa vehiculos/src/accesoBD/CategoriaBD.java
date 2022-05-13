package accesoBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Categoria;
import clases.Oficina;
import exceptions.Codigo_no_valido;
import exceptions.Descripcion_no_valida;
import exceptions.Localidad_no_valida;
import exceptions.Opcion_no_valida;
import exceptions.Provincia_no_valida;
import exceptions.Recargo_no_valido;

public class CategoriaBD {
	
	public static Categoria listaCategoria(String codigo) throws Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido {
		ResultSet resultadoSql=null;
		Categoria categoria=null;
		PreparedStatement ps;
			try {
				ps = Conexion.conexion.prepareStatement("SELECT * FROM categoria WHERE codigo=?");
				ps.setString(1, codigo);
				resultadoSql=ps.executeQuery();
				while(resultadoSql.next()) {
					categoria= new Categoria(codigo,resultadoSql.getString("descripcion"),resultadoSql.getInt("recargo"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		
	return categoria;
	}
	
	public static void eliminaCategoria(String codigo) throws SQLException {
		boolean hay=false;
		PreparedStatement ps=Conexion.conexion.prepareStatement("SELECT matricula FROM vehiculos,categoria WHERE categoria_codigo=codigo");
		ResultSet resultadoSql=ps.executeQuery();
		
		while(resultadoSql.next()) {//si entra en el bucle quiere decir que hay vehiculos en esta categoria
			hay=true;
			break;
		}
			
		if(hay) {//Si hay vehiculos se eliminaran con la categoria
			ps=Conexion.conexion.prepareStatement("delete categoria,vehiculos from categoria join vehiculos on codigo=categoria_codigo where codigo=?");
			ps.setString(1, codigo);
			ps.executeUpdate();
		}else {//Si no hay solo se elimina la categoria
			ps=Conexion.conexion.prepareStatement("delete categoria from categoria where codigo=?");
			ps.setString(1, codigo);
			ps.executeUpdate();
		}
		
	}
	
	public static void añadeCategoria(String codigo,String descripcion,int recargo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("INSERT INTO categoria VALUES (?,?,?)");
		ps.setString(1, codigo);
		ps.setString(2, descripcion);
		ps.setInt(3, recargo);
		
		ps.executeUpdate();
	}
	
	public static void modificaCategoria(String codigo,String descripcion,int recargo) throws SQLException {
		PreparedStatement ps=Conexion.conexion.prepareStatement("UPDATE categoria SET descripcion=?,recargo=? WHERE codigo=?");
		ps.setString(1, descripcion);
		ps.setInt(2, recargo);
		ps.setString(3,codigo);
		
		ps.executeUpdate();
	}
	
public static ArrayList<Categoria> listaCategorias() throws SQLException, Codigo_no_valido, Descripcion_no_valida, Recargo_no_valido {
		
		ArrayList<Categoria> listaCategoria=new ArrayList<Categoria>();
		ResultSet resultadoSql=Conexion.instruccionSelect(Conexion.conexion,"SELECT * FROM categoria");
		Categoria categoria;
		while(resultadoSql.next()) {
			categoria=new Categoria(resultadoSql.getString("codigo"),resultadoSql.getString("descripcion"),resultadoSql.getInt("recargo"));
			listaCategoria.add(categoria);
		}
		return listaCategoria;
	}
	
}
