package pkg001;

public class Boleto {

    String zona;
    int edad;
    int precioZona;
    double descuentoEstudiante = 0;
    double descuentoEdad = 0;
    int precioFinal;
    String aplicaDescuentoEstudiante = "";
    String aplicaDescuentoEdad = "";

    Boleto(String zona,
        int edad,
        int precioZona,
        double descuentoEstudiante,
        double descuentoEdad,
        String aplicaDescuentoEstudiante,
        String aplicaDescuentoEdad,
        int precioFinal) {

        this.zona = zona;
        this.edad = edad;
        this.precioZona = precioZona;
        this.descuentoEstudiante = descuentoEstudiante;
        this.descuentoEdad = descuentoEdad;        
        this.aplicaDescuentoEstudiante = aplicaDescuentoEstudiante;
        this.aplicaDescuentoEdad = aplicaDescuentoEdad;
        this.precioFinal = precioFinal;        
    }

    void imprimir() {   

        if(descuentoEstudiante != 0) {
            aplicaDescuentoEstudiante = "Descuento Estudiante: 10%" + "\n";
         }
 
         if(descuentoEdad != 0) {
             aplicaDescuentoEdad = "Descuento Tercera edad: 15%" + "\n";
         }

        System.out.println(("----------------------------------"+"\n"+
                            "Zona: " + zona + "\n" +
                            "Precio base: $ " + precioZona + "\n" +
                            aplicaDescuentoEstudiante +
                            aplicaDescuentoEdad +
                            "Precio final: $ " + precioFinal + " pesos" + "\n" +
                            "----------------------------------"+"\n"));

    }
}
