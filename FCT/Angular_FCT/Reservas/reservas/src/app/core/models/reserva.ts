export class Reserva {
  private _usuario_id: Number;
  private _fecha_Entrada: Date;
  private _fecha_Salida: Date;
  private _numero_personas: any;
  private _total: Number;
  private _date: Date[];
  private PRICE = 50;

  constructor(id_user: number) {
    this._usuario_id = id_user;
    this._fecha_Entrada = new Date();
    this._fecha_Salida = new Date();
    this._numero_personas = 1;
    this._total = 0;
    this._date = [];
  }

  toJSON() {
    return {
      usuario_id: this._usuario_id,
      fecha_Entrada: this.fecha_Entrada,
      fecha_Salida: this.fecha_Salida,
      numero_personas: this._numero_personas.key,
      importeTotal:this.total
    };
  }

  /**
   * Getter usuario_id
   * @return {Number}
   */
  public get usuario_id(): Number {
    return this._usuario_id;
  }

  /**
   * Getter fecha_Entrada
   * @return {Date}
   */
  public get fecha_Entrada(): Date {
    return this._fecha_Entrada;
  }

  /**
   * Getter fecha_Salida
   * @return {Date}
   */
  public get fecha_Salida(): Date {
    return this._fecha_Salida;
  }

  /**
   * Getter precio Total
   * @return {Number}
   */
  public get total(): Number {
    if (this._date.length <= 0) return 0;
    if (!this._fecha_Salida || !this._fecha_Entrada) return 0;
    return (
      ((this._fecha_Salida?.getTime() - this._fecha_Entrada?.getTime()) /
        (1000 * 60 * 60 * 24)) *
        this.PRICE +
      50
    );
  }

  /**
   * Getter numero_persona
   * @return {Number}
   */
  public get numero_personas(): Number {
    return this._numero_personas;
  }
  /**
   * Getter fechas
   * @return {Date[]}
   */
  public get date(): Date[] {
    return this._date;
  }

  /**
   * Setter usuario_id
   * @param {Number} value
   */
  public set usuario_id(value: Number) {
    this._usuario_id = value;
  }

  /**
   * Setter fecha_Entrada
   * @param {Date} value
   */
  public set fecha_Entrada(value: Date) {
    this._fecha_Entrada = value;
  }

  /**
   * Setter fecha_Salida
   * @param {Date} value
   */
  public set fecha_Salida(value: Date) {
    this._fecha_Salida = value;
  }

  /**
   * Setter numero_persona
   * @param {any} value
   */
  public set numero_personas(value: any) {
    this._numero_personas = value;
  }
  /**
   * Setter fechas
   * @param {Date[]} value
   */
  public set date(value: Date[]) {
    this._date = value;
    this.fecha_Entrada = this._date[0];
    this._fecha_Salida = this._date[1];
  }
}
