package coin;

import java.util.ArrayList;
import java.util.List;

public class CoinAcceptor {

    public List<Coin> coinList;

    public CoinAcceptor() {
        this.coinList = new ArrayList<>();
    }

    public void addCoin(Coin coin) {
        coinList.add(coin);
    }

    public int getTotalInserted() {
        return coinList.stream().mapToInt(Coin::getValue).sum();
    }

    public List<Coin> returnAllCoins() {
        List<Coin> returned = new ArrayList<>(coinList);
        coinList.clear();
        return returned;
    }

    public void clear() {
        coinList.clear();
    }


}
