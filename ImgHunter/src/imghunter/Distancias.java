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
	 * dist�ncia cityBlock, que � a soma das diferencas absolutas
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



}
