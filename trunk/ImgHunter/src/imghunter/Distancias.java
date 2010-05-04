package imghunter;


public class Distancias {
	/**
	 * calculaDistanciaEuclidiana - a partir de dois vetores
	 * calcula a dist�ncia euclidiana entre os dois
	 *  
	 * @return - em caso de vetores com dimens�es diferentes, retorna null
	 */
	
	public Double calculaDistanciaEuclidiana(Integer[] fig1, Integer[] fig2)
	{
	  Double sum = null;

	if(fig1.length == fig2.length)
	{
	  sum = 0.0;
	  for(int i = 0; i< fig1.length; i++ )
		sum += Math.pow(fig1[i] - fig2[i], 2);

		sum = Math.sqrt(sum);
		
		}
		return sum;	

	}

	/**
	 * calculaDistanciaXadrez- dados dois vetores, calcula qual
	 *  � a maior dist�ncia entre seus elementos
	 *
	 * @return - em caso de vetores com dimens�es diferentes, retorna null
	 */
	public Double calculaDistanciaXadrez( Integer[] fig1, Integer[] fig2 )
	{
		Double greater = null;
		if(fig1.length == fig2.length)
		{  greater = 0.0;
			for(int i=0; i< fig1.length ; i++)
				if(Math.abs(fig1[i]- fig2[i]) > greater )
					greater = Math.abs((fig1[i]- fig2[i])*1.0);
		}
		return greater;
	}
	/**
	 * calculaDistanciaCityBlock - dados dois vetores, calcula a 
	 * distância cityBlock, que é a soma das diferencas absolutas
	 * entre todas as dimens�es.
	 * 
	 * @return - em caso de vetores com dimens�es diferentes, retorna null
	 */
	public Double calculaDistanciaCityBlock(Integer[] fig1, Integer[]fig2)
	{
		Double sum = null;
		if(fig1.length == fig2.length)
		{
			sum = 0.0;
			for(int i = 0; i< fig1.length; i++)
				sum += Math.abs(fig1[i]- fig2[i]);
		}
	return sum;
	
	}


/**
 * calculaDistanciaModeloVetorial - dados dois vetores
 * calcula a distância entre eles utilizando o modelo
 * vetorial
 * 
 *  @return - em caso de vetores com dimens�es diferentes, retorna null
 */
public Double calculaDistanciaModeloVetorial(Integer fig1[], Integer fig2[])
        {
        	Double dist = null, produtoEscalar = 0.0,
                        modulofig1 = 0.0,modulofig2 = 0.0 ;
		if(fig1.length == fig2.length)
		{
			dist = 0.0;
			for(int i = 0; i< fig1.length; i++)
                        {	produtoEscalar += (fig1[i] * fig2[i]);
                                modulofig1 += fig1[i] * fig1[i];
                                modulofig2 += fig2[i] * fig2[i];
                                 }
                    dist = produtoEscalar /((Math.sqrt(modulofig1)* Math.sqrt(modulofig2)));
                    }

                return dist;



        }
}
