package co.edu.uniquindio.laboratoriocolecciones.persistencia;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/



import co.edu.uniquindio.laboratoriocolecciones.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Persistencia {


    //--------------------------------------RUTAS----------------------------------------
    public static final String QUEUE_NUEVA_PUBLICACION = "nueva_publicacion";

    public static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/clientesTxtt";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/productoTxt";

    public static final String RUTA_ARCHIVO_VENTA = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/ventaTxt";
    public static final String RUTA_ARCHIVO_DETALLE_VENTA = "src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/detalleVentaTxt";
    public static final String RUTA_ARCHIVO_CARRITO_COMPRA = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/carritosCompras";

    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param
     * @throws IOException
     */
//-------------------------------------------GUARDAR ARCHIVOS------------------------------
    //clientes
    public static void guardarClientes(HashMap<String, Cliente> listaClientes) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Cliente cliente : listaClientes.values()) {
            contenido += cliente.getNumeroId() + "--" + cliente.getNombre() + "--" + cliente.getDireccion();
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
    }

    //productoss
    public static void guardarProdcutos(HashMap<String, Producto> listaProductos) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (Producto producto : listaProductos.values()) {
            contenido += producto.getCodigo() + "--" + producto.getNombreProducto() + "--" + producto.getPrecio() + "--" + producto.getCantidad();
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
    }

    //detalle venta
    public static void guardarDetalleVenta(ArrayList<DetalleVenta> detalleVentas) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (DetalleVenta detalleVenta : detalleVentas) {
            contenido += detalleVenta.getCantidad() + "--" + detalleVenta.getSubtotal() + "--" + detalleVenta.getCodigoDetaleVenta() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_DETALLE_VENTA, contenido, false);
    }

    public static void guardarCarritoCompras(HashSet<CarritoCompras> listaCarritoCompras) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        for (CarritoCompras carritoCompras : listaCarritoCompras) {
            contenido += carritoCompras.getCodigo();
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
    }



//	--------------------------------------------CARGAR ARCHIVOS----------------------------------------------------------

    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

    //productos
    public static HashMap<String, Cliente> cargarClientes() throws FileNotFoundException, IOException {
        HashMap<String, Cliente> clientes = new HashMap<String, Cliente>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Cliente cliente = new Cliente();
            cliente.setNumeroId(linea.split("--")[0]);
            cliente.setNombre(linea.split("--")[1]);
            cliente.setDireccion(linea.split("--")[2]);
            clientes.put(cliente.getNumeroId(), cliente);
        }
        return clientes;
    }

    public static HashMap<String, Producto> cargarProductos() throws FileNotFoundException, IOException {
        HashMap<String, Producto> productos = new HashMap<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Producto producto = new Producto();
            producto.setCodigo(linea.split("--")[0]);
            producto.setNombreProducto(linea.split("--")[1]);
            producto.setPrecio(Integer.valueOf(linea.split("--")[2]));
            producto.setCantidad(Integer.valueOf(linea.split("--")[3]));
            productos.put(producto.getCodigo(), producto);
        }
        return productos;
    }


    public static ArrayList<DetalleVenta> cargarDetalleVenta() throws FileNotFoundException, IOException {
        ArrayList<DetalleVenta> detalleVentas = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_DETALLE_VENTA);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setCantidad(Integer.valueOf(linea.split("--")[0]));
            detalleVenta.setSubtotal(linea.split("--")[1]);
            detalleVenta.setCodigoDetaleVenta(linea.split("--")[2]);
            detalleVentas.add(detalleVenta);
        }
        return detalleVentas;
    }

    public static HashSet<CarritoCompras> cargarCarritoCompras() throws FileNotFoundException, IOException {
        HashSet<CarritoCompras> listaCarritoCompras = new HashSet<CarritoCompras>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            CarritoCompras carritoCompra = new CarritoCompras();
            carritoCompra.setCodigo(linea.split("--")[0]);
            listaCarritoCompras.add(carritoCompra);
        }
        return listaCarritoCompras;
    }
}
