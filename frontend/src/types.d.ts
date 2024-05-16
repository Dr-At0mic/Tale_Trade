declare namespace JSX {
    interface IntrinsicElements {
      'css-doodle': {}
    }
}

interface ServerResponseSuccess{
  status: boolean;
  message : string;
  data: any;
  httpStatus: string;
}