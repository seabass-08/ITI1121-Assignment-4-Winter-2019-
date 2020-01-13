public class Jaccard implements Similarity{
  /**
    * Calculates a similarity measure between two documents represented
    * by their WordMap objects.
    *
    * @param a the WordMap of document A
    * @param b the WordMap of document break;
    * @return the similarity measure of the two frequency vectors
    * @throws NullPointerException if any of the two parameters is NullPointerException
    * @throws IllegalArgumentException if any of the two WordMap object has size 0
    */

    public double score(WordMap a, WordMap b){
        WordMap AIB = new TreeWordMap();
        WordMap AUB = new TreeWordMap();
        double score;

        String[] keysA = a.keys();
        String[] keysB = b.keys();
        int shortlen;

        for (int i = 0; i<keysA.length; i++){
            String tmpKey = keysA[i];
            AUB.update(tmpKey);

            if (b.contains(tmpKey)){
                AIB.update(tmpKey);
            }
        }

        for (int i = 0; i<keysB.length; i++){
            String tmpKey = keysB[i];
            AUB.update(tmpKey);

            if(a.contains(tmpKey)){
                AIB.update(tmpKey);
            }
        }

        score = (double)( AIB.size() ) / ( AUB.size() );

        return score;
    }
}
