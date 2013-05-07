package com.pember.downturn

import org.bson.types.ObjectId

class Stock {

    ObjectId id
    String symbol
    String name
    List dailyClosings
    Date dateCreated
    Date lastUpdated

    static belongsTo = [user:User]

    static mapWith = "mongo"
    static hasMany = [dailyClosings: BigDecimal]

    static constraints = {
        symbol blank: false, nullable: false, maxSize: 7
        name blank: false, nullable: false, maxSize: 100
    }

    String toString() {
        "${name} (${symbol})"
    }
}
