var conn = new Mongo();
var db = conn.getDB('example');

// Messages
db.createCollection('messages');
db.messages.save({
    "message": "test message"
});
