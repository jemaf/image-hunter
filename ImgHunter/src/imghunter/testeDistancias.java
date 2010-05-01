package imghunter;


public class testeDistancias {
	
	public static void main(String args[])
	{
		Distancias teste = new Distancias(); 
		Integer f1[] = {0,0},
		f2[]= {1,1};
		System.out.print("Euclidiana : \t");
		System.out.println(teste.calculaDistanciaEuclidiana(f1, f2));
		System.out.print("City Block : \t");
		System.out.println(teste.calculaDistanciaCityBlock(f1, f2));
		System.out.print("Xadrez : \t");
		System.out.println(teste.calculaDistanciaXadrez(f1, f2));
		
	}
	
	
}
