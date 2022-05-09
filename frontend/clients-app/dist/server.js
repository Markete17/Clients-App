let express = require('express');
let path = require('path');
let app = express();
let port = 8089;

app.use(express.static('clients-app'));

app.get('*',(req, res,next) => {
    res.sendFile(path.resolve('clients-app/index.html'));
})

app.listen(port, () =>{
    console.log("Server express init in port: "+port);
});