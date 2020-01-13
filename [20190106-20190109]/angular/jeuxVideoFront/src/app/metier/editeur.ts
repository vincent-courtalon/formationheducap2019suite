import { JeuxVideo } from './jeux-video';

export class Editeur {
    constructor(public id : number,
                public nom: string,
                public email: string,
                public jeuxVideos? : JeuxVideo[]){}
}