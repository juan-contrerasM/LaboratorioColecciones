package co.edu.uniquindio.laboratoriocolecciones.persistencia;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/



import co.edu.uniquindio.laboratoriocolecciones.model.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static co.edu.uniquindio.laboratoriocolecciones.persistencia.GestorProductos.obtenerProductoPorCodigo;


public class Persistencia {


    //--------------------------------------RUTAS----------------------------------------
    public static final String QUEUE_NUEVA_PUBLICACION = "nueva_publicacion";

    public static final String RUTA_ARCHIVO_CLIENTES = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/clientesTxtt";
    public static final String RUTA_ARCHIVO_PRODUCTOS = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/productoTxt";

    public static final String RUTA_ARCHIVO_VENTA = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/ventaTxt";
    public static final String RUTA_ARCHIVO_DETALLE_VENTA = "LaboratorioColecciones/src/main/resources/co/edu/uniquindio/laboratoriocolecciones/persistencia/detalleVentaTxt";
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

    public static void guardarCarritoCompras(CarritoCompras carritoDeCompras) throws IOException {
        String contenido = "";
        for (Map.Entry<String, Producto> entry : carritoDeCompras.getProductos().entrySet()) {
            // Asumiendo que cada producto en el HashMap también tiene una cantidad
            contenido += entry.getKey() + "," + entry.getValue().getCantidad() + "\n"; // Separador ',' entre código y cantidad, '\n' para nueva línea
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

    public static HashMap<String, Producto> cargarProductos() {
        HashMap<String, Producto> productos = new HashMap<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(RUTA_ARCHIVO_PRODUCTOS));
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    String codigo = partes[0];
                    String nombreProducto = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    int cantidad = Integer.parseInt(partes[3]);
                    Producto producto = new Producto(codigo, nombreProducto, precio, cantidad);
                    productos.put(codigo, producto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

    public static CarritoCompras cargarCarritoCompras() throws FileNotFoundException, IOException {
        CarritoCompras carritoDeCompras = new CarritoCompras();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);
        for (String linea : contenido) {
            String[] partes = linea.split(","); // Asumiendo que cada línea tiene el formato "código,cantidad"
            if (partes.length == 2) {
                String codigo = partes[0];
                int cantidad = Integer.parseInt(partes[1]);
                // Asumiendo que tienes una manera de obtener un producto por su código
                Producto producto = obtenerProductoPorCodigo(codigo);
                if (producto != null) {
                    carritoDeCompras.agregarProducto(producto, cantidad);
                }
            }
        }
        return carritoDeCompras;
    }
}
