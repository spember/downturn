describe("addClass", function() {
    var div;

    beforeEach(function () {
        div = document.createElement("div");
        expect(div.className).toBe("");
    });

    it("Should add a class when none exists", function () {
        downturn.utils.addClass(div, "testing");
        expect(div.className).toBe("testing");
    });

    it("Should not add the same class twice", function () {
        div.setAttribute("class", "testing");
        expect(div.className).toBe("testing");
        downturn.utils.addClass(div, "testing");
        expect(div.className).toBe("testing");

        downturn.utils.addClass(div, "foo");
        expect(div.className).toBe("testing foo");
        downturn.utils.addClass(div, "foo");
        expect(div.className).toBe("testing foo");
    });

    it("Should add a class compared by whole words", function () {

        div.setAttribute("class", "testing-final testingFoo");
        expect(div.className).toBe("testing-final testingFoo");
        downturn.utils.addClass(div, "testing");
        expect(div.className).toBe("testing-final testingFoo testing");

    });
});

describe("removeClass", function () {
    var div;

    beforeEach(function () {
        div = document.createElement("div");
        div.setAttribute("class", "testing");
        expect(div.className).toBe("testing");
    });

    it("Should have no effect on an empty className", function () {
        div.setAttribute("class", "");
        expect(div.className).toBe("");

        downturn.utils.removeClass(div, "foo");
        expect(div.className).toBe("");
    });

    it("Should remove a class from className when only one exists", function () {
        downturn.utils.removeClass(div, "testing");
        expect(div.className).toBe("");
    });

    it("Should only extract the targeted className", function () {
        div.setAttribute("class", "foo testing bar");
        downturn.utils.removeClass(div, "testing");
        expect(div.className).toBe("foo bar");

        div.setAttribute("class", "foo testing Stesting-minor bar");
        downturn.utils.removeClass(div, "testing");
        expect(div.className).toBe("foo Stesting-minor bar");
    });



});
