import kotlin.system.exitProcess

val UP:Int = 1
val DOWN:Int = -1
val LEFT:Int = -2
val RIGHT:Int = 2
var numSteps : Int = 0
val maxIterations = 100
val steps = IntArray(maxIterations)  { i -> 0 }

class MatrixGame (var dimension: Int =3) {
    // var dimension : Int = 3

    fun printDimension() {
        println("Matrix dimension is $dimension")
    }
}

fun tracePrincess(princess_x: Int, princess_y: Int, dragon_x: Int, dragon_y: Int) :Int {

    var direction : Int = -1

    if (dragon_x > princess_x) {
        direction = LEFT
    }
    if ( dragon_x < princess_x ){
        direction = RIGHT
    }
    if ( dragon_y > princess_y) {
        direction = UP
    }
    if ( dragon_y < princess_y){
        direction = DOWN
    }
    steps[numSteps] = direction
    return direction
}

fun DisplayMatrix(princess_x: Int, princess_y: Int, dragon_x: Int, dragon_y: Int, dimension: Int) {

    if (dimension < 3) {
        println("Matrix dimension less than 3. Cannot display")
    } else if (dimension > 50) {
        println("Matrix dimension greater than 50. Cannot display")
    } else {

        for (i in 1..dimension) {
            for (j in 1..dimension) {

                if (j == princess_x && i == princess_y &&  j == dragon_x && i == dragon_y) {
                    print("DP")
                } else
                if (j == princess_x && i == princess_y) {
                    print("P")
                } else
                if (j == dragon_x && i == dragon_y) {
                        print("D")
                } else

                        print("_ ")

                }
                println()
            }
        }
        println()
    }

class Princess (var x:Int = 1, var y: Int = 1) {


}
class Dragon (var x:Int = 1, var y: Int = 1) {


}



fun main(args: Array<String>) {
    val matrix = MatrixGame()
    val princess = Princess()
    var dragon = Dragon()
    var i = 0
    print("Enter the dimensions of the matrix: ")
    val matrixDimension = readLine()!!
    //println("you entered matrix dimension = $matrixDimension")
    matrix.dimension = matrixDimension.toInt()
    matrix.printDimension()
    if (matrix.dimension >50 || matrix.dimension <3) {
        println("Invalid matrix dimension. Keep it between 3 and 50")
        exitProcess(-1)
    }

    princess.x = (1..matrix.dimension).shuffled().last()
    princess.y = (1..matrix.dimension).shuffled().last()
    dragon.x = (1..matrix.dimension).shuffled().last()
    dragon.y = (1..matrix.dimension).shuffled().last()

   // println("$princess.x $pr $random3 $random4")

    DisplayMatrix(princess.x,princess.y, dragon.x, dragon.y, matrix.dimension)
    var step : Int = -1

    for (i in 1..maxIterations) {
        if (dragon.x == princess.x && dragon.y == princess.y) {
            break

        }
        step = tracePrincess(princess.x,princess.y, dragon.x, dragon.y)
        if (step == LEFT) dragon.x = dragon.x - 1
        else if (step == RIGHT) dragon.x = dragon.x +1
        else if (step == DOWN) dragon.y = dragon.y + 1
        else if (step == UP) dragon.y = dragon.y -1

        numSteps++
        //println("px=${princess.x},py= ${princess.y},dx= ${dragon.x}, dy=${dragon.y}")
        DisplayMatrix(princess.x,princess.y, dragon.x, dragon.y, matrix.dimension)
    }
    print("Dragon found and devoured the princess after $numSteps moves (")
    i = 0
    while ( steps[i] != 0 ) {

        if (steps[i] == UP) print("UP, ")
        else if (steps[i] == DOWN) print("DOWN, ")
        else if (steps[i] == LEFT) print("LEFT, ")
        else if (steps[i] == RIGHT) print("RIGHT, ")
        i++
    }
    print( "Bon Apetit!)")

}
