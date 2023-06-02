ALTER TABLE articulos ADD FOREIGN KEY(id_proveedor) REFERENCES proveedores(id);
ALTER TABLE detalle_ventas ADD FOREIGN KEY(id_articulo) REFERENCES articulos(codigo);
ALTER TABLE detalle_ventas ADD FOREIGN KEY(id_venta) REFERENCES ventas(id);
ALTER TABLE ventas ADD FOREIGN KEY(id_empleado) REFERENCES empleados(id);