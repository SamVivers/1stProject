import React, { Component } from 'react';
export default class ShowTask extends Component {
    constructor(props){
        super(props);
        this.state={
            view: 0,
            desc: "",
            pri: "",
            stat: "",
            date: "",
            reverseDate: ""
        }
    }
    componentWillMount() {
        this.displayDate();
    }
    displayDate(){
        let input = this.props.date;
        let output = "";
        console.log(input.length);
        console.log(input);
        for (let i = input.length; i > 0; i--) {
            let cutHere = input.length;
            if (input.substring(i - 1, i).equals("-") || i === 1) {
                output += input.substring(i - 1, cutHere);
                cutHere = i - 1;
            }
        }
        this.setState({
            reverseDate: output
        })
    }

    render() {
        if (!this.state.view) {    
            return (          
                <div class="grid-container" id={this.props.id}>
                    <div>{this.props.desc}</div>
                    <div>{this.props.pri}</div>
                    <div>{this.props.stat}</div>
                    <div>{this.state.reverseDate}</div>
                    <div>
                        <button id="edit" onClick={this.edit}>edit</button>
                        <button id="delete" onClick={this.remove}>delete</button>
                    </div>
                </div>
            )
        } else {
            return (
                <div class="grid-container">
                    <div><textarea type="text" id="task" placeholder={this.props.desc} className="user-input" 
                    onChange={this.changeDesc} rows="1" ></textarea></div>
                    <div><select id="priority" placeholder={this.props.pri} onChange={this.changePri}>
                        <option value="init"></option>
                        <option value="low">low</option>
                        <option value="medium">medium</option>
                        <option value="high">high</option>
                        <option value="top">TOP</option>
                    </select></div>
                    <div><select id="status" onChange={this.changeStat} placeholder={this.props.stat}>
                        <option value="init"></option>
                        <option value="not-started">not started</option>
                        <option value="started">started</option>
                        <option value="progressing">progressing</option>
                        <option value="finishing-up">finishing up</option>
                        <option value="completed">completed</option>
                    </select></div> 
                    <div><input type="date" id="date" onChange={this.changeDate} class="user-input" required="required"/></div>                        
                    <div>
                        <button onClick={this.update} id="update">update</button>
                        <button onClick={this.remove} id="update">delete</button>
                    </div>
                 </div>
            )
        }
    }

    changeDesc=(a)=>{
        this.setState({
            desc:a.target.value
        });
    }

    changePri=(b)=>{
        this.setState({
            pri:b.target.value
        });
    }

    changeStat=(c)=>{
        this.setState({
            stat:c.target.value
        });
    }

    changeDate=(d)=>{
        this.setState({
            date:d.target.value
        });
    }
    edit=()=>{
        this.setState({
            view:1
        })
    }

    update=()=>{
        let URL='http://localhost:8081/api/tasklist/tasks/' + this.props.id;
        let request = new XMLHttpRequest();
        request.open('PUT', URL);
        request.setRequestHeader("Content-Type", "application/json");
        request.setRequestHeader("Accept", "application/json");
        request.responseType = 'json';
        request.onload=()=> {
            this.props.getTasks();
        }
        let descriptionCh = this.state.desc;
        if (descriptionCh === "") {
            descriptionCh = this.props.desc;
        }
        let priorityCh = this.state.pri;
        if (priorityCh === "") {
            priorityCh = this.props.pri;
        }
        let statusCh = this.state.stat;
        if (statusCh === "") {
            statusCh = this.props.stat;
        }
        let dateCh = this.state.date;
        if (dateCh === "") {
            dateCh = this.props.date;
        }
        let body = {
                    id: this.props.id,
                    username: this.props.user,
                    description: descriptionCh,
                    priority: priorityCh,
                    status: statusCh,
                    do_by: dateCh 
                    };
        body = JSON.stringify(body);
        request.send(body);
        this.setState({
            view:0
        })
    }

    remove=()=>{
        let URL='http://localhost:8081/api/tasklist/tasks/' + this.props.id;
        let request = new XMLHttpRequest();
        request.open('DELETE', URL);
        request.responseType = 'json';
        request.onload=()=> {
            this.props.getTasks();
        }
        request.send();
        this.setState({
            view:0
        })
    }
}