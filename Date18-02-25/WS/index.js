// <!--
// Employee Management WebSocket Application

// Objective:
// -----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js. 
// This system will allow clients to:
//     1. Insert Employee Records (INSERT <name> <salary>)
//     2. Retrieve Employee List (RETRIEVE)
//     3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
// Your goal is to implement and test a WebSocket-based server and client, 
// ensuring that all operations work correctly.

// Requirements:
// -------------
// 1. Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections.
// 		-> Process client messages and handle commands:
// 			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
// 			2. RETRIEVE → Returns all stored employees.
// 			3. Any other command should return "Invalid command."
// 		-> Maintain an in-memory array of employees (no database required).
// 		-> Log each received command on the console.
		
		
// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			Server Response
// ============================================================================================
// INSERT Alice 50000		"Employee inserted successfully."
// INSERT Bob 60000		"Employee inserted successfully."
// RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
//                         "ID: 2, Name: Bob, Salary: 60000"
// INVALID					"Invalid command."
// ============================================================================================

// Note: 
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080
// --></salary>

const ws = require('ws');

const wss = new ws.Server({ port: 8080 });

let employees = [];

wss.on('connection', (ws) => {
    console.log('New client connected');
    ws.on('message', (message) => {
        console.log(message);
        const [command, ...dets] = message.toString().split(' ');
        if (command === 'INSERT') {
            const name = dets[0];
            const salary = parseFloat(dets[1]);
            employees.push({ id: employees.length+1, name, salary });
            ws.send('Employee inserted successfully.');
        }
        else if (command === 'RETRIEVE') {
            employees.forEach((employee) => {
                ws.send(`ID: ${employee.id}, Name: ${employee.name}, Salary: ${employee.salary}`);
            });
        }
        else {
            ws.send('Invalid command.');
        }
    });

    ws.on('close', () => {
        console.log('Client disconnected');
    });
});
console.log('WebSocket server is running on ws://localhost:8080');
