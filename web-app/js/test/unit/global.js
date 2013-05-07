//test namespace
describe("Namespace", function() {
    it("contains spec with an expectation", function() {
        expect(true).toBe(true);
    });
    it("creates an object in window, which is an empty object", function () {
        namespace("test");
        expect(window.test).toBeDefined();
        expect(window.test).toEqual({});
    });
    it("creates sub objects in window", function () {
        namespace("test.foo");
        expect(window.test).toBeDefined();
        expect(window.test.foo).toBeDefined();
        expect(window.test.foo).toEqual({});
        expect(window.test).toEqual({foo:{}});
    });
    it("does not override existing objects", function () {
        window.test = {
            "x": 6
        }
        namespace("test");
        expect(window.test).toBeDefined();
        expect(window.test.x).toBeDefined();
        expect(window.test.x).toEqual(6);

        window.test.foo = {
            "y": 7,
            "bar": 5
        }
        namespace("test.foo");
        expect(window.test.x).toEqual(6);
        expect(window.test.foo.y).toEqual(7);
        expect(window.test.foo.bar).toEqual(5);
    });
});

