import java.math.BigDecimal

class Chapter8 {
}

interface Account4<E : Account4<E>> : Comparable<E> {
    val balance: BigDecimal
    override fun compareTo(other: E): Int =
        balance.compareTo(other.balance)
}