export class Utilisateur {
    constructor(public id : number,
                public login: string,
                public enabled : boolean,
                public roles ? : any[]) {}
}