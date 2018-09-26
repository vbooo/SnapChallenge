package mypromotion.vboo.com.snapchallenge.model

/**
 * Created by fabie on 17/09/2018.
 */
class Challenge {

    private var id: Int? = null
    private var earnPoints: Int? = null
    private var objectiveTime: Int? = null
    private var releasedTime: Int? = null
    private var idAuthor: Int? = null
    private var succeed: Boolean? = null
    private var idTypeChallenge: Int? = null

    constructor(id: Int?, earnPoints: Int?, objectiveTime: Int?, releasedTime: Int?, idAuthor: Int?, succeed: Boolean?) {
        this.id = id
        this.earnPoints = earnPoints
        this.objectiveTime = objectiveTime
        this.releasedTime = releasedTime
        this.idAuthor = idAuthor
        this.succeed = succeed
    }

}