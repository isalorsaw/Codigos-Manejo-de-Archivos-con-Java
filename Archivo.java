import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
public class Archivo
{
	FileWriter output;
	String nombre;
	public static void main(String e[])
	{
		try
		{
			Archivo arc;
			arc=new Archivo("informacion.txt");
			String info=arc.traeString();
			JOptionPane.showMessageDialog(null,""+info);
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(null,"No se Encuentra Archivo de Fallas");
		}		
	}
	public Archivo(String nombArch)
	{
		try
		{
			File app=new File(nombArch);
			nombre=app.getCanonicalPath();				
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"No se Encuentra Archivo de Fallas");
		}
	}
	String traeString()
	{
		String lineas[]=new String[size()],cadena="";
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();		
		try{
                    	entrada=new FileReader(nombre);
				int c;
				while((c=entrada.read())!=-1)
				{
                            cadena+=(char)c;
				}
		}
		catch(Exception ex)
		{
		    System.out.println("Error al Cargar");
		}	
		return cadena;
	}	
	String[] traerLineas()
	{
		String lineas[]=new String[size()],acum="";
		int i=0;
		try
		{
		    File x=new File(nombre);
                    	FileReader xr=new FileReader(x);
			int c;
			while(true)
			{
                            c=xr.read();
                            if(c!=10&&c!=13&&c!=-1)acum+=(char)c+"";				
                            if(c==10)
                            {			
                            	lineas[i]=acum;
                            	i++;	
                            	acum="";
                            }
                            if(c==-1)break;
			}			
		}
		catch(Exception e)
		{
        		System.out.println("No se encuentra "+nombre);
                }
        	return lineas;
        }
	public int size()
	{
        	int cont=0;
		FileReader entrada=null;
		StringBuffer str=new StringBuffer();
		
		try
		{
        		entrada=new FileReader(nombre);
				int c;
				while((c=entrada.read())!=-1)
				{
                            if((char)c=='\n')cont++;
				}
		}
		catch(Exception ex)
		{
		    System.out.println("Error al Cargar");
		}
        	return cont;
	}
	public void insertar(String ultima) throws IOException
	{	
        	String lineas[]=traerLineas();
		PrintWriter g=new PrintWriter(nombre);
		g.flush();
		for(int i=0;i<=lineas.length-1;i++)
		{
        		g.write(lineas[i]);
                        g.println();
		}
		g.write(ultima);
		g.println();
		g.close();
	}
	public void actualizar(String ultima) throws IOException
	{	
		PrintWriter g=new PrintWriter(nombre);
		g.flush();
		g.write(ultima);
		g.println();
		g.close();
	}
}
