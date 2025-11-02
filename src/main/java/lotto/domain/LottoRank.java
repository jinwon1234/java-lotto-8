package lotto.domain;

public enum LottoRank {

    FIRST(1, 2_000_000_000L,6, false),
    SECOND(2, 30_000_000L,5, true),
    THIRD(3, 1_500_000L,5,false),
    FOURTH(4, 50_000L,4,false),
    FIFTH(5, 5_000L,3,false),
    NONE(-1, 0L,0,false);

    private final int rank;
    private final long prize;
    private final int matchCount;
    private final boolean matchBonusNumber;

    LottoRank(int rank, long prize, int matchCount, boolean matchBonusNumber) {
        this.rank = rank;
        this.prize = prize;
        this.matchCount = matchCount;
        this.matchBonusNumber = matchBonusNumber;
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

    public static LottoRank calculateRank(int matchCount, boolean matchBonusNumber) {
        for (LottoRank rank : values()) {
            if (rank.matchCount == matchCount && rank.matchBonusNumber == matchBonusNumber) {
                return rank;
            }
        }
        return NONE;
    }
}
