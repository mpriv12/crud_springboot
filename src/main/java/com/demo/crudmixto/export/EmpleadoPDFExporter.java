package com.demo.crudmixto.export;

import com.demo.crudmixto.entity.Empleado;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmpleadoPDFExporter {

    private List<Empleado> listaEmpleados;

    public EmpleadoPDFExporter(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        document.add(new Paragraph("Lista de Empleados", font));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        table.addCell("ID");
        table.addCell("Nombre");
        table.addCell("Email");

        for (Empleado e : listaEmpleados) {
            table.addCell(e.getId().toString());
            table.addCell(e.getNombre());
            table.addCell(e.getEmail());
        }

        document.add(table);
        document.close();
    }
}
