describe("messageService", function() {
    var messageService;
    beforeEach(function () {
        messageService = new downturn.services.messageService();
    });


    it("Should start with no messages", function () {
        expect(messageService.messages.length).toBe(0);
    });

    it("Should allow one to add messages", function () {
        messageService.add("This is a test");
        expect(messageService.messages.length).toBe(1);
        expect(messageService.messages[0]).toBe("This is a test");
    });

    it("Should return all messages on retrieve, and delete them", function () {
        messageService.add("Msg 1");
        messageService.add("Msg 2");
        messageService.add("Msg 3");
        expect(messageService.messages.length).toBe(3);
        expect(messageService.retrieve().length).toBe(3);
        expect(messageService.messages.length).toBe(0);
    });
});
