package lotto.domain;

public enum LottoRank {

    FIRST(1, 2_000_000_000L,6),
    SECOND(2, 30_000_000L,5),
    THIRD(3, 1_500_000L,5),
    FOURTH(4, 50_000L,4),
    FIFTH(5, 5_000L,3),
    NONE(-1, 0L,0);

    private final int rank;
    private final long prize;
    private final int matchCount;

    LottoRank(int rank, long prize, int matchCount) {
        this.rank = rank;
        this.prize = prize;
        this.matchCount = matchCount;
    }

    public int getRank() {
        return rank;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
