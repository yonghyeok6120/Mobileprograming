open class Base(var name: String){
    open fun ass() = println("정용혁")
}

class Derived(name: String) : Base(name){
    override fun ass(){
        println("정용혁입니다")
    }
}

fun main(){
    val derived = Derived(name = "정용혁")
    println("Derived: ${derived.name}")
    derived.ass()
    val base= Base(name = " ")
    base.ass()
}