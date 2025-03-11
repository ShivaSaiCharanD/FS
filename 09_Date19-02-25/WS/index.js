const ws = require('ws');
const mongoose = require('mongoose');
const AutoIncrement = require('mongoose-sequence')(mongoose);

mongoose.connect('mongodb://localhost:27017/employee', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
}, { _id: false });

employeeSchema.plugin(AutoIncrement, { id: 'employee_counter', inc_field: '_id' });

const Employee = mongoose.model('Employee', employeeSchema);

const wss = new ws.Server({ port: 8080 });

wss.on('connection', ws => {
    console.log('Client Connected');

    ws.on('message', async (message) => {
        const messageStr = message.toString().trim();
        console.log('Received:', messageStr);

        const [command, ...dets] = messageStr.split(' ');

        if (command === "INSERT") {
            const [name, salary, role, department, experience] = dets;
            try {
                const employee = new Employee({
                    name,
                    salary,
                    role,
                    department,
                    experience
                });

                await employee.save();
                ws.send(`Employee inserted successfully.ID: 1`);
            } catch (error) {
                ws.send("Error inserting employee.");
                console.error(error);
            }

        } else if (command === "RETRIEVE") {
            try {
                const employees = await Employee.find({});
                if (employees.length === 0) {
                    ws.send("No employees found.");
                } else {
                    await Promise.all(
                        employees.map(employee => 
                            ws.send(`ID: ${employee._id}, Name: ${employee.name}, Salary: ${employee.salary}, Role: ${employee.role}, Department: ${employee.department}, Experience: ${employee.experience} years`)
                        )
                    );
                }
            } catch (error) {
                ws.send("Error retrieving employees.");
                console.error(error);
            }

        } else if (command === "RETRIEVE_BY_DEPT") {
            if (!dets.length) {
                ws.send("Invalid command. Provide a department.");
                return;
            }

            try {
                const department = dets[0];
                const employees = await Employee.find({ department });
                if (employees.length === 0) {
                    ws.send(`No employees found in department: ${department}`);
                } else {
                    await Promise.all(
                        employees.map(employee => 
                            ws.send(`ID: ${employee._id}, Name: ${employee.name}, Salary: ${employee.salary}, Role: ${employee.role}, Department: ${employee.department}, Experience: ${employee.experience} years`)
                        )
                    );
                }
            } catch (error) {
                ws.send("Error retrieving employees by department.");
                console.error(error);
            }

        } else {
            ws.send("Invalid command.");
        }
    });
});

console.log("WebSocket server is running on ws://localhost:8080");
