import com.pember.downturn.Role
import com.pember.downturn.User
import com.pember.downturn.UserRole

class BootStrap {

    def init = { servletContext ->
        if (User.count() == 0) {
            // setup two roles and admin user
            Role member = new Role(authority:"member").save()
            Role admin = new Role(authority:"administrator").save()

            User exchange = new User(username: "exchange", password: "secret").save()
            new UserRole(user: exchange, role:  admin).save()
            print "Initial account created"
        }

    }
    def destroy = {
    }
}
