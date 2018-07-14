public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++)
            deque.addLast(word.charAt(i));
        return deque;
    }

    public boolean isPalindrome(String word){
        if (word.length() == 0 || word.length() == 1)
            return true;
        boolean res = true;
        for (int i = 0, j = word.length() - 1; i <= j; i++, j --){
            res = res && word.charAt(i) == word.charAt(j);
        }

        return res;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1)
            return true;
        boolean res = true;
        for (int i = 0, j = word.length() - 1; i < j; i++, j--){
            res = res && cc.equalChars(word.charAt(i),word.charAt(j));
        }
        return res;
    }
}
