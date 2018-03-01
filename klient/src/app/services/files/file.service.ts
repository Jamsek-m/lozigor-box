import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AuthService} from "../auth/auth.service";

@Injectable()
export class FileService {
    private URL_FILES = "http://localhost:8080/api/v1/datoteke";


    constructor(private http: HttpClient, private auth: AuthService) {

    }

    public pridobiFile(id: number) {
        const url = `${this.URL_FILES}/${id}`;
        return this.http.get(url, {
            headers: this.generirajToken(),
            responseType: "blob"
        });
    }

    public shraniFile(parent_id: number, files: FileList) {
        const url = this.URL_FILES;
        const forma: FormData = new FormData();
        forma.append("parent", parent_id.toString());
        for (let i = 0; i < files.length; i++) {
            forma.append("files", files.item(i), files.item(i).name);
        }
        return this.http.post(url, forma, {headers: this.generirajTokenMultipart()})
            .toPromise()
            .then(res => console.log("datoteke so uploadane"))
            .catch(err => console.log("NAPAKA: ", err));
    }

    private generirajToken(): HttpHeaders {
        return new HttpHeaders({
            "Content-Type": "application/json",
            "Authorization": "Bearer " + this.auth.vrniZeton()
        });
    }

    private generirajTokenMultipart(): HttpHeaders {
        return new HttpHeaders({
            "Authorization": "Bearer " + this.auth.vrniZeton()
        });
    }



}
