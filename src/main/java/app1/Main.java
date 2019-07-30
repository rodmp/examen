package app1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import app1.config.AppConfig;
import app1.service.ServicioImpl;

@Component
public class Main {

	@Autowired
	private ServicioImpl servicio;
	
	public static void main(String[] args) {
		if(args.length < 2) {
			System.out.println("** Error de ejecución, Favor de ejecutar algunas de las opciones: add, list, search");
		}
		
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Main main = context.getBean(Main.class);
		
		if(args[1].equals("add")) {
			main.executeAdd(formatJson(args[2]));
		} else if(args[1].equals("list")) {
			main.executeList();
		} else if(args[1].equals("fuzzy-search")) {
			main.executeSearch(formatJson(args[2]));
		}
	}
	
	private static String formatJson(String string) {
		String[] split = string.substring(1, string.length() - 1).split(":");
		
		return  "{" + "\"" + split[0]+ "\"" + ":" + "\"" + split[1] + "\"" + "}";
	}

	private void executeSearch(String arg) {
		System.out.println(servicio.search(arg));
	}

	private void executeList() {
		System.out.println(servicio.list());
	}

	public void executeAdd(String arg) {
		System.out.println(servicio.add(arg));
	}
}
