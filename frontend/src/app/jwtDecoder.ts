import jwt_decode from 'jwt-decode';

export function jwtDecoder() {
  const jwt = localStorage.getItem('jwt');

  let payload: any;
  if (jwt != null) {
    payload = jwt_decode(jwt);
    return payload;
  } else return null;
}
