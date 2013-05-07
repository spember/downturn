package com.pember.downturn

import org.bson.types.ObjectId

class User {

	transient springSecurityService
    ObjectId id
	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

    static mapWith = "mongo"

    static hasMany = [stocks:Stock]

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
        if (springSecurityService) {
            password = springSecurityService.encodePassword(password)
        }

	}

    String toString() {
        "User: '$username'"
    }
}
